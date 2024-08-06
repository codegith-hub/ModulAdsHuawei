package com.adsmedia.adsmodul;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adsmedia.mastermodul.MasterAdsHelper;
import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AdsHelper {
    public static boolean openads = false;
    public static boolean debugMode;
    public static boolean directData = false;

    public static void gdpr(Activity activity, Boolean childDirected, String keypos,String gameAppId) {


    }

    public static void initializeAds(Activity activity, int pos) {
    }

    public static void initializeAds(Activity activity, String pos, String gameId, boolean test) {
        HwAds.init(activity);
        MasterAdsHelper.initializeAds(activity, pos);
    }

    public static void initializeAds(Activity activity, String pos, String gameId) {
        HwAds.init(activity);
        MasterAdsHelper.initializeAds(activity, pos);
    }

    public static void debugMode(Boolean debug) {
        debugMode = debug;
        MasterAdsHelper.debugMode(debug);
    }

    public static BannerView bannerView;

    public static void showBanner(Activity activity, RelativeLayout layout, String huaweiId) {

        bannerView = new BannerView(activity);
        bannerView.setAdId(huaweiId);
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_320_50);
        layout.addView(bannerView);
        bannerView.loadAd(new AdParam.Builder().build());
        AdListener adListener = new AdListener();
        bannerView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailed(int errorCode) {
                MasterAdsHelper.showBanner(activity, layout);
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onAdLeave() {
            }

            @Override
            public void onAdClosed() {
            }
        });

    }

    public static InterstitialAd interstitialAd;

    public static void loadInterstitial(Activity activity, String huaweiId) {
        directData = true;
        try {
            interstitialAd = new InterstitialAd(activity);
            interstitialAd.setAdId(huaweiId);
            AdParam adParam = new AdParam.Builder().build();
            interstitialAd.loadAd(adParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MasterAdsHelper.loadInterstitial(activity);
    }

    public static int count = 0;

    public static void showInterstitial(Activity activity, String yandexId, int interval) {
        if (count >= interval) {
            if (interstitialAd != null && interstitialAd.isLoaded()) {
                interstitialAd.show(activity);
            } else {
                MasterAdsHelper.showInterstitial(activity);
            }
            count = 0;
            loadInterstitial(activity, yandexId);
        } else {
            count++;
        }
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            //Logger.logStackTrace(TAG,e);
        }
        return "";
    }
}
