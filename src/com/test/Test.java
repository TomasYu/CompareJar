package com.test;

import com.zhangyue.campare.tools.Command;
import sun.tools.java.ClassFile;
import sun.tools.java.ClassPath;
import sun.tools.java.Identifier;
import sun.tools.java.Package;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * Created by zy1 on 16/11/2017.
 */
public class Test {
    public static void main(String []args){
        String classString="public abstract class com.zhangyue.iReader.app.ui.ActivityBase extends com.zhangyue.iReader.t                                                                            \n" +
                "ements com.zhangyue.iReader.ui.fragment.base.CoverFragmentManager$CoverFragmentManagerDelegat                                                                            \n" +
                "erver$NightChangeObserver,android.os.Handler$Callback,com.zhangyue.iReader.toolbar.IToolbar {                                                                            \n" +
                "  private static final java.lang.String TAG;                                                                                                                             \n" +
                "  protected boolean mIsStoped;                                                                                                                                           \n" +
                "  protected com.zhangyue.iReader.ui.window.WindowControl mControl;                                                                                                       \n" +
                "  private com.zhangyue.iReader.app.HandlerMessageHelper<com.zhangyue.iReader.app.ui.ActivityB                                                                            \n" +
                "  protected java.lang.Object mDialogParam;                                                                                                                               \n" +
                "  private com.zhangyue.iReader.ui.view.widget.dialog.helper.ProgressDialogHelper mProgressDia                                                                            \n" +
                "  protected com.zhangyue.iReader.ui.view.widget.dialog.AlertDialogController mAlertDialog;                                                                               \n" +
                "  public android.os.Handler mHandler;                                                                                                                                    \n" +
                "  public static long mCurrentTime;                                                                                                                                       \n" +
                "  public static final long SHOW_AD_INTERVAL;                                                                                                                             \n" +
                "  private com.zhangyue.iReader.View.box.listener.NightAnimationInterface mNightShadowView;                                                                               \n" +
                "  private boolean mIsSupportNight;                                                                                                                                       \n" +
                "  public static final java.lang.String DISABLE_EXIT_ANIM;                                                                                                                \n" +
                "  protected boolean mIsDisableExitAnim;                                                                                                                                  \n" +
                "  protected com.zhangyue.iReader.ui.fragment.base.CoverFragmentManager mFragmentManager;                                                                                 \n" +
                "  protected com.zhangyue.iReader.Platform.Share.ShareDialog mShareDialog;                                                                                                \n" +
                "  protected com.zhangyue.iReader.toolbar.ZYToolbar mToolbar;                                                                                                             \n" +
                "  protected boolean isNeedStartBookShelf;                                                                                                                                \n" +
                "  protected long mStartOpenBookTime;                                                                                                                                     \n" +
                "  private static final long REFRESH_READTIME_INTERVAL;                                                                                                                   \n" +
                "  protected com.zhangyue.iReader.ui.view.widget.dialog.helper.ListDialogHelper mListDialogHel                                                                            \n" +
                "  private com.zhangyue.iReader.ui.view.widget.dialog.IDefaultFooterListener mListenerResult;                                                                             \n" +
                "  public android.content.res.Resources mMyResources;                                                                                                                     \n" +
                "  private java.lang.reflect.Field mClassLoaderField;                                                                                                                     \n" +
                "  private boolean mIsInitClassLoaderField;                                                                                                                               \n" +
                "  protected boolean mSetWriteSetting;                                                                                                                                    \n" +
                "  public boolean mForceScreenOn;                                                                                                                                         \n" +
                "  protected boolean mIsScreenOn;                                                                                                                                         \n" +
                "  public int mScreenTimeOut;                                                                                                                                             \n" +
                "  public java.lang.Runnable mOffScreenRunnable;                                                                                                                          \n" +
                "  private static boolean sInited;                                                                                                                                        \n" +
                "  private static java.lang.reflect.Method msetDrawDuringWindowsAnimatingMethod;                                                                                          \n" +
                "  public com.zhangyue.iReader.app.ui.ActivityBase();                                                                                                                     \n" +
                "  public void setDialogListener(com.zhangyue.iReader.app.APP$OnDialogEventListener, java.lang                                                                            \n" +
                "  public void setDialogEventListener(com.zhangyue.iReader.ui.view.widget.dialog.IDefaultFoote                                                                            \n" +
                "  public void setDialogEventListener(com.zhangyue.iReader.ui.view.widget.dialog.IDefaultFoote                                                                            \n" +
                ".content.DialogInterface$OnKeyListener);                                                                                                                                 \n" +
                "  public void onWindowFocusChanged(boolean);                                                                                                                             \n" +
                "  protected void closeWelcomeActivity();                                                                                                                                 \n" +
                "  public com.zhangyue.iReader.ui.view.widget.dialog.AlertDialogController getAlertDialogContr                                                                            \n" +
                "  public void setContentView(int);                                                                                                                                       \n" +
                "  public android.os.Handler getHandler();                                                                                                                                \n" +
                "  public boolean isStoped();                                                                                                                                             \n" +
                "  public boolean handleMessage(android.os.Message);                                                                                                                      \n" +
                "  protected void onHandleMessage(android.os.Message);                                                                                                                    \n" +
                "  protected void setCurrAcvitity();                                                                                                                                      \n" +
                "  protected void setWindowControl();                                                                                                                                     \n" +
                "  public com.zhangyue.iReader.ui.window.WindowControl getWindowControl();                                                                                                \n" +
                "  private void cleanCurrActivity();                                                                                                                                      \n" +
                "  protected void onCreate(android.os.Bundle);                                                                                                                            \n" +
                "  public void addContentView(android.view.View, android.view.ViewGroup$LayoutParams);                                                                                    \n" +
                "  public void resetStatusBar();                                                                                                                                          \n" +
                "  protected void beforeOnCreate();                                                                                                                                       \n" +
                "  protected int getStatusBarBgColor();                                                                                                                                   \n" +
                "  public int getContentPaddingTop();                                                                                                                                     \n" +
                "  public final boolean isTransparentStatusBarAble();                                                                                                                     \n" +
                "  protected boolean isSupportNight();                                                                                                                                    \n" +
                "  protected boolean isSupportTranslucentBar();                                                                                                                           \n" +
                "  private void initDialogProgress();                                                                                                                                     \n" +
                "  public boolean isDialogProgressShown();                                                                                                                                \n" +
                "  public com.zhangyue.iReader.app.APP$OnDialogEventListener getDialogProgressListener();                                                                                 \n" +
                "  protected boolean isEnableGuesture();                                                                                                                                  \n" +
                "  public boolean isReadingPage();                                                                                                                                        \n" +
                "  protected void beEventOnStop();                                                                                                                                        \n" +
                "  protected boolean isTXT();                                                                                                                                             \n" +
                "  protected boolean isAppLockPage();                                                                                                                                     \n" +
                "  protected void onStart();                                                                                                                                              \n" +
                "  protected void onRestart();                                                                                                                                            \n" +
                "  protected void onResume();                                                                                                                                             \n" +
                "  public void setShareDialog(com.zhangyue.iReader.Platform.Share.ShareDialog);                                                                                           \n" +
                "  public void dismissShareDialog();                                                                                                                                      \n" +
                "  protected void checkNessaryPermisson();                                                                                                                                \n" +
                "  protected boolean isSupportStartShowAd();                                                                                                                              \n" +
                "  protected void onPause();                                                                                                                                              \n" +
                "  protected void onStop();                                                                                                                                               \n" +
                "  protected void onDestroy();                                                                                                                                            \n" +
                "  public void showProgressDialog(java.lang.String);                                                                                                                      \n" +
                "  public void showProgressDialog(java.lang.String, com.zhangyue.iReader.app.APP$OnDialogEvent                                                                            \n" +
                "  public void showProgressDialog(java.lang.String, com.zhangyue.iReader.app.APP$OnDialogEvent                                                                            \n" +
                "  public void cancelProgressDialog();                                                                                                                                    \n" +
                "  public void hideProgressDialog();                                                                                                                                      \n" +
                "  public void setDialogParam(java.lang.Object);                                                                                                                          \n" +
                "  public static void setFullScreen(android.app.Activity, boolean);                                                                                                       \n" +
                "  protected void dealWithRefreshReadTime();                                                                                                                              \n" +
                "  public static void hideTitleBar(android.app.Activity);                                                                                                                 \n" +
                "  public static void setScreenVertical(android.app.Activity);                                                                                                            \n" +
                "  public static void setScreenHorizontal(android.app.Activity);                                                                                                          \n" +
                "  public static void hideSoftInput(android.app.Activity);                                                                                                                \n" +
                "  public static void adjustSoftInput(android.app.Activity);                                                                                                              \n" +
                "  public boolean alertSdcard();                                                                                                                                          \n" +
                "  public boolean isShowDialog();                                                                                                                                         \n" +
                "  public void tryDismissDialog();                                                                                                                                        \n" +
                "  protected void doScreenOrientation();                                                                                                                                  \n" +
                "  protected void onGotoSettingWireless();                                                                                                                                \n" +
                "  protected void onGotoSettingWifi();                                                                                                                                    \n" +
                "  protected void onGotoNetSeting();                                                                                                                                      \n" +
                "  public void showSystemStatusBar();                                                                                                                                     \n" +
                "  public void hideSystemStatusBar();                                                                                                                                     \n" +
                "  public void setBrightnessToSystem();                                                                                                                                   \n" +
                "  public void setBrightnessToConfig();                                                                                                                                   \n" +
                "  public boolean isScreenPortrait();                                                                                                                                     \n" +
                "  protected void onPostCreate(android.os.Bundle);                                                                                                                        \n" +
                "  public com.zhangyue.iReader.View.box.listener.NightAnimationInterface getNightShadowView();                                                                            \n" +
                "  public android.view.View findViewById(int);                                                                                                                            \n" +
                "  public void setGuestureEnable(boolean);                                                                                                                                \n" +
                "  public void isRunInBackground(boolean);                                                                                                                                \n" +
                "  protected void attachBaseContext(android.content.Context);                                                                                                             \n" +
                "  public android.content.res.Resources getResources();                                                                                                                   \n" +
                "  public android.content.res.Resources$Theme getTheme();                                                                                                                 \n" +
                "  public java.lang.Object getSystemService(java.lang.String);                                                                                                            \n" +
                "  public android.content.Context getApplicationContext();                                                                                                                \n" +
                "  public java.lang.ClassLoader getClassLoader();                                                                                                                         \n" +
                "  protected void showSystemSettingConfimAlert();                                                                                                                         \n" +
                "  public com.zhangyue.iReader.ui.fragment.base.CoverFragmentManager getCoverFragmentManager()                                                                            \n" +
                "  public void onNightChanged();                                                                                                                                          \n" +
                "  public boolean dispatchKeyEvent(android.view.KeyEvent);                                                                                                                \n" +
                "  public boolean onKeyDown(int, android.view.KeyEvent);                                                                                                                  \n" +
                "  public boolean onKeyUp(int, android.view.KeyEvent);                                                                                                                    \n" +
                "  public boolean dispatchTouchEvent(android.view.MotionEvent);                                                                                                           \n" +
                "  public void onBackPressed();                                                                                                                                           \n" +
                "  public void finishWithoutAnimation();                                                                                                                                  \n" +
                "  protected void onActivityResult(int, int, android.content.Intent);                                                                                                     \n" +
                "  private void initSysStatusBar();                                                                                                                                       \n" +
                "  protected boolean isDarkStatus();                                                                                                                                      \n" +
                "  public void onThemeChanged(boolean);                                                                                                                                   \n" +
                "  public void startActivity(android.content.Intent);                                                                                                                     \n" +
                "  public void startActivityForResult(android.content.Intent, int);                                                                                                       \n" +
                "  public void restScreenOn();                                                                                                                                            \n" +
                "  public void offScreenOn();                                                                                                                                             \n" +
                "  public void setScreenOn();                                                                                                                                             \n" +
                "  public void finish();                                                                                                                                                  \n" +
                "  public final void onMultiWindowModeChanged(boolean);                                                                                                                   \n" +
                "  public void onConfigurationChanged(android.content.res.Configuration);                                                                                                 \n" +
                "  public void onCustomMultiWindowChanged(boolean);                                                                                                                       \n" +
                "  public boolean isInMultiWindowBottom();                                                                                                                                \n" +
                "  public boolean isInMultiWindow();                                                                                                                                      \n" +
                "  public boolean customIsInMultiWindow();                                                                                                                                \n" +
                "  public boolean isEnableImmersive();                                                                                                                                    \n" +
                "  protected void initToolbar();                                                                                                                                          \n" +
                "  protected boolean isThemeToolbar();                                                                                                                                    \n" +
                "  public void assembleToolbar();                                                                                                                                         \n" +
                "  public boolean onToolMenuItemClick(android.view.MenuItem);                                                                                                             \n" +
                "  public void onNavigationClick(android.view.View);                                                                                                                      \n" +
                "  public void onAttachedToWindow();                                                                                                                                      \n" +
                "  static void access$000(com.zhangyue.iReader.app.ui.ActivityBase);                                                                                                      \n" +
                "  static com.zhangyue.iReader.ui.view.widget.dialog.helper.ProgressDialogHelper access$100(co                                                                            \n" +
                "se);                                                                                                                                                                     \n" +
                "  static {};                                                                                                                                                             \n" +
                "}                                                                                                                                                                        ";

    }

    private static void tsst1() {
        String s = Command.exeCmd("javap -c -private C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin\\com\\zhangyue\\iReader\\cache\\VolleyLoader.class");
        System.out.println(s);
        String[] split = s.split("\n");


        System.out.println("aaaa");
//        int a = 20;
////        Path path = Paths.get("C:/iReader_plugin");
////        Path jarFile = Files.createFile(path);
//
//        File file = new File("c://iReader_plugin.jar");
//
//        JarFile jarFile = null;
//        try {
//            jarFile = new JarFile(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Enumeration<JarEntry> entries = jarFile.entries();
//        while (entries.hasMoreElements()) {
//            JarEntry jarEntry = entries.nextElement();
//            System.out.print(jarEntry.getClass());
//        }

        ClassPath classPath = new ClassPath("c://iReader_plugin.jar");
        URL[] urls = new URL[0];
        try {
            urls = new URL[]{new URL("jar:file:c://iReader_plugin.jar!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            Identifier identifier = Identifier.lookup("com");
            try {
                Package pkg = new Package(classPath, identifier);
                Enumeration binaryFiles = pkg.getBinaryFiles();
                while (binaryFiles.hasMoreElements()) {
                    ClassFile classFile = (ClassFile) binaryFiles.nextElement();
                    classFile.getName();
                    // -6 because of .class
                    String className = classFile.getName().substring(0, classFile.getName().length() - 6);
                    if (className.contains("$")) {
                        continue;
                    }
                    Command.exeCmd("javap -c -private C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin\\com\\zhangyue\\iReader\\cache\\");
//                    className = className.replace('/', '.');
//                    Class c = cl.loadClass(className);
//                    System.out.println(className);
//
//                    Field[] fields = c.getFields();
//                    for (Field field : fields) {
//                        String name = field.getName();
////                        System.out.println(name);
//                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
