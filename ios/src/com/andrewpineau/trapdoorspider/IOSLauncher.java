package com.andrewpineau.trapdoorspider;

import java.util.ArrayList;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.gamekit.GKAchievement;
import org.robovm.apple.gamekit.GKLeaderboard;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.bindings.admob.GADAdSize;
import org.robovm.bindings.admob.GADBannerView;
import org.robovm.bindings.admob.GADBannerViewDelegateAdapter;
import org.robovm.bindings.admob.GADRequest;
import org.robovm.bindings.admob.GADRequestError;
import org.robovm.bindings.gamecenter.GameCenterListener;
import org.robovm.bindings.gamecenter.GameCenterManager;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;


public class IOSLauncher extends IOSApplication.Delegate implements ActivityRequestHandler{
	
	private GameCenterManager gcManager;
    private static final boolean USE_TEST_DEVICES = true;
    private GADBannerView adview;
    private boolean adsInitialized = false;
    private boolean loggedIn = false;
    private boolean loginFailed = false;
    private String leaderboardID = "trapdoor_spider_leaderboard";
    private IOSApplication iosApplication;

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.orientationLandscape = false;
        config.orientationPortrait = true;
        iosApplication = new IOSApplication(new TSGame(this), config);
        return iosApplication;
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
    

    @Override
    public GameCenterManager getGameCenterManager(){
    	if(gcManager == null){
    		gcManager = new GameCenterManager(UIApplication.getSharedApplication().getKeyWindow(), new GameCenterListener() {
	            @Override
	            public void playerLoginFailed (NSError error) {
	                loggedIn = false;
	                loginFailed = true;
	            }

	            @Override
	            public void playerLoginCompleted () {
	                loggedIn = true;
	            }

	            @Override
	            public void achievementReportCompleted () {
	            }

	            @Override
	            public void achievementReportFailed (NSError error) {
	            }

	            @Override
	            public void achievementsLoadCompleted (ArrayList<GKAchievement> achievements) {	                
	            }

	            @Override
	            public void achievementsLoadFailed (NSError error) {
	            }

	            @Override
	            public void achievementsResetCompleted () {
	            }

	            @Override
	            public void achievementsResetFailed (NSError error) {
	            }

	            @Override
	            public void scoreReportCompleted () {
	            }

	            @Override
	            public void scoreReportFailed (NSError error) {
	            }

	            @Override
	            public void leaderboardsLoadCompleted (ArrayList<GKLeaderboard> scores) {
	            }

	            @Override
	            public void leaderboardsLoadFailed (NSError error) {
	            }

	            @Override
	            public void leaderboardViewDismissed () {
	            }

	            @Override
	            public void achievementViewDismissed () {
	            }
	        });
    	}
    	
    	return gcManager;
    }
    
    @Override
    public boolean isLoggedIn(){
    	return loggedIn;
    }
    
    @Override
    public boolean getLoginFailed(){
    	 return loginFailed;
    }
    
    public String getLeaderboardID(){
    	return leaderboardID;
    }
    
    
    
    public void hide() {
        initializeAds();

        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        double screenWidth = screenSize.getWidth();

        final CGSize adSize = adview.getBounds().getSize();
        double adWidth = adSize.getWidth();
        double adHeight = adSize.getHeight();



        float bannerWidth = (float) screenWidth;
        float bannerHeight = (float) (bannerWidth / adWidth * adHeight);

        adview.setFrame(new CGRect(0, -bannerHeight, bannerWidth, bannerHeight));
    }

   
    public void show() {
        initializeAds();

        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        double screenWidth = screenSize.getWidth();

        final CGSize adSize = adview.getBounds().getSize();
        double adWidth = adSize.getWidth();
        double adHeight = adSize.getHeight();

        float bannerWidth = (float) screenWidth;
        float bannerHeight = (float) (bannerWidth / adWidth * adHeight);

        adview.setFrame(new CGRect((screenWidth / 2) - adWidth / 2, 0, bannerWidth, bannerHeight));
    }

    public void initializeAds() {
        if (!adsInitialized) {

            adsInitialized = true;
            adview = new GADBannerView(GADAdSize.smartBannerPortrait());
            adview.setAdUnitID("ca-app-pub-9537006426209080/4352639254"); //put your secret key here
            adview.setRootViewController(iosApplication.getUIViewController());
            iosApplication.getUIViewController().getView().addSubview(adview);

            final GADRequest request = GADRequest.create(); //FIX???
            /*if (USE_TEST_DEVICES) {
                final NSArray<?> testDevices = new NSArray<NSObject>(
                        new NSString(GADRequest.GAD_SIMULATOR_ID));
                request.setTestDevices((List<String>) testDevices);
            }*/
            
            adview.setDelegate(new GADBannerViewDelegateAdapter() {
                @Override
                public void didReceiveAd(GADBannerView view) {
                    super.didReceiveAd(view);
                }

                @Override
                public void didFailToReceiveAd(GADBannerView view,
                        GADRequestError error) {
                    super.didFailToReceiveAd(view, error);
                }
            });

            adview.loadRequest(request);

        }
    }

    public void showAds(boolean show) {
    	
        initializeAds();

        final CGSize screenSize = UIScreen.getMainScreen().getBounds().getSize();
        double screenWidth = screenSize.getWidth();

        final CGSize adSize = adview.getBounds().getSize();
        double adWidth = adSize.getWidth();
        double adHeight = adSize.getHeight();

        float bannerWidth = (float) screenWidth;
        float bannerHeight = (float) (bannerWidth / adWidth * adHeight);

        if(show) {
            adview.setFrame(new CGRect((screenWidth / 2) - adWidth / 2, 0, bannerWidth, bannerHeight));
        } else {
            adview.setFrame(new CGRect(0, -bannerHeight, bannerWidth, bannerHeight));
        }
    }
}