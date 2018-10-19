package prototypez.github.io.sq;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import androidx.fragment.app.FragmentTransaction;
import io.reactivex.Observable;
import prototypez.github.io.sq.fragment.ActivityResultFragment;
import prototypez.github.io.sq.fragment.SupportActivityResultFragment;

public class Sq {

    @AnimatorRes @AnimRes
    private static int enter = R.anim.sq_push_in_left;
    @AnimatorRes @AnimRes
    private static int exit = R.anim.sq_push_out_left;
    @AnimatorRes @AnimRes
    private static int popEnter = R.anim.sq_push_in_right;
    @AnimatorRes @AnimRes
    private static int popExit = R.anim.sq_push_out_right;

    /**
     * set Fragment Push-in Pop-out Animation
     */
    public static void setFragmentAnimation(@AnimatorRes @AnimRes int enter,
                                            @AnimatorRes @AnimRes int exit,
                                            @AnimatorRes @AnimRes int popEnter,
                                            @AnimatorRes @AnimRes int popExit) {
        Sq.enter = enter;
        Sq.exit = exit;
        Sq.popEnter = popEnter;
        Sq.popExit = popExit;
    }

    // region setResult

    public static void setResult(AppCompatActivity activity, int resultCode) {
        setResult(activity, resultCode, null);
    }

    public static void setResult(Activity activity, int resultCode) {
        setResult(activity, resultCode, null);
    }

    public static void setResult(AppCompatActivity activity, int resultCode, Intent resultData) {

        if (resultData == null) {
            resultData = new Intent();
        }

        Bundle requestData = activity.getIntent().getExtras();

        if (requestData != null) {
            resultData.putExtra(SqConstant.KEY_REQUEST_DATA, requestData);
        }

        activity.setResult(resultCode, resultData);
    }

    public static void setResult(Activity activity, int resultCode, Intent resultData) {

        if (resultData == null) {
            resultData = new Intent();
        }

        Bundle requestData = activity.getIntent().getExtras();

        if (requestData != null) {
            resultData.putExtra(SqConstant.KEY_REQUEST_DATA, requestData);
        }

        activity.setResult(resultCode, resultData);
    }

    // endregion

    // region obtainActivityResult

    public synchronized static Observable<ActivityResult> obtainActivityResult(AppCompatActivity activity) {
        return SupportActivityResultFragment.getActivityResultObservable(activity);
    }

    public synchronized static Observable<ActivityResult> obtainActivityResult(androidx.fragment.app.Fragment v4Fragment) {
        return SupportActivityResultFragment.getActivityResultObservable(v4Fragment);
    }


    public synchronized static Observable<ActivityResult> obtainActivityResult(Activity activity) {
        return ActivityResultFragment.getActivityResultObservable(activity);
    }

    public synchronized static Observable<ActivityResult> obtainActivityResult(Fragment fragment) {
        return ActivityResultFragment.getActivityResultObservable(fragment);
    }

    // endregion

    // region insertActivityResult
    public synchronized static void insertActivityResult(AppCompatActivity activity, ActivityResult activityResult) {
        SupportActivityResultFragment.insertActivityResult(activity, activityResult);
    }

    public synchronized static void insertActivityResult(androidx.fragment.app.Fragment v4Fragment, ActivityResult activityResult) {
        SupportActivityResultFragment.insertActivityResult(v4Fragment, activityResult);
    }

    public synchronized static void insertActivityResult(Activity activity, ActivityResult activityResult) {
        ActivityResultFragment.insertActivityResult(activity, activityResult);
    }

    public synchronized static void insertActivityResult(Fragment fragment, ActivityResult activityResult) {
        ActivityResultFragment.insertActivityResult(fragment, activityResult);
    }
    // endregion

    // region startActivityForResult

    public static void startActivityForResult(AppCompatActivity activity, Intent intent, int requestCode) {
        startActivityForResult(activity, intent, requestCode, null);
    }

    public static void startActivityForResult(androidx.fragment.app.Fragment v4Fragment, Intent intent, int requestCode) {
        startActivityForResult(v4Fragment, intent, requestCode, null);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int requestCode) {
        startActivityForResult(activity, intent, requestCode, null);
    }

    public static void startActivityForResult(Fragment fragment, Intent intent, int requestCode) {
        startActivityForResult(fragment, intent, requestCode, null);
    }

    public static void startActivityForResult(AppCompatActivity activity, Intent intent, int requestCode, Bundle requestContextData) {
        if (requestContextData != null && requestContextData.size() > 0) {
            intent.putExtra(SqConstant.KEY_REQUEST_CONTEXT_DATA, requestContextData);
        }
        SupportActivityResultFragment.startActivityForResult(activity, intent, requestCode);
    }

    public static void startActivityForResult(androidx.fragment.app.Fragment v4Fragment, Intent intent, int requestCode, Bundle requestContextData) {
        if (requestContextData != null && requestContextData.size() > 0) {
            intent.putExtra(SqConstant.KEY_REQUEST_CONTEXT_DATA, requestContextData);
        }
        SupportActivityResultFragment.startActivityForResult(v4Fragment, intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int requestCode, Bundle requestContextData) {
        if (requestContextData != null && requestContextData.size() > 0) {
            intent.putExtra(SqConstant.KEY_REQUEST_CONTEXT_DATA, requestContextData);
        }
        ActivityResultFragment.startActivityForResult(activity, intent, requestCode);
    }

    public static void startActivityForResult(Fragment fragment, Intent intent, int requestCode, Bundle requestContextData) {
        if (requestContextData != null && requestContextData.size() > 0) {
            intent.putExtra(SqConstant.KEY_REQUEST_CONTEXT_DATA, requestContextData);
        }
        ActivityResultFragment.startActivityForResult(fragment, intent, requestCode);
    }

    // endregion

    // region findOrCreateFragment

    public  static <T extends androidx.fragment.app.Fragment> T findOrCreateFragment(@NonNull Class<T> v4FragmentClass, AppCompatActivity activity, String tag) {
        androidx.fragment.app.FragmentManager v4FragmentManager = activity.getSupportFragmentManager();
        T v4Fragment = (T) v4FragmentManager.findFragmentByTag(tag);
        if (v4Fragment == null) {
            try {
                v4Fragment = v4FragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return v4Fragment;
    }

    public  static <T extends Fragment> T findOrCreateFragment(@NonNull Class<T> fragmentClass, Activity activity, String tag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        T fragment = (T) fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

    // endregion

    // region push

    public static void push(AppCompatActivity activity, androidx.fragment.app.Fragment v4Fragment, String tag) {
        List<androidx.fragment.app.Fragment> currentFragments = activity.getSupportFragmentManager().getFragments();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if (currentFragments.size() != 0) {
            transaction.setCustomAnimations(
                    enter,
                    exit,
                    popEnter,
                    popExit
            );
        }
        transaction.add(R.id.fragment_container, v4Fragment, tag);
        if (currentFragments.size() != 0) {
            transaction
                    .hide(currentFragments.get(currentFragments.size() - 1))
                    .addToBackStack(tag);
        }
        transaction.commit();
    }

    public static void push(AppCompatActivity activity, androidx.fragment.app.Fragment v4Fragment) {
        push(activity, v4Fragment, v4Fragment.getClass().getCanonicalName());
    }

    // TODO Support Plain Activity
    public static void push(Activity activity, Fragment fragment, String tag) {
        throw new RuntimeException("Not supported currently, Please use AppCompatActivity as host instead.");
    }

    public static void push(Activity activity, Fragment fragment) {
        push(activity, fragment, fragment.getClass().getCanonicalName());
    }

    // endregion
}
