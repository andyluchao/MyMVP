package com.yundotech.mymvp.contract;

import android.content.Context;
import android.os.Bundle;

public class IBaseContract {
    public enum BaseState {
        NONE,  // default, nothing is ready
        INITIALIZED,  // data is initialized
        SHOWING,  // UI is showing but not focused on
        RUNNING   // UI is showing and focused on, so that operation is available
    }
    public interface IBasePresenter<IView extends IBaseView> {
        /**
         * save context for some initialization depending on context.
         * @param context the activity's context passing to presenter while fragment is attached to activity
         */
        void onAttach(Context context);

        /**
         * Initialize presenter with saved information in Bundle.
         * Start some timer or thread job for all but not UI.
         * @param savedInfo passing from activity
         */
        void onCreate(Bundle savedInfo);

        /**
         * Bind IBaseView to IBasePresenter, and start some timer or thread job for UI.
         * @param view sub-class of BaseFragment in MyMvp framework
         */
        void onStart(IView view);

        /**
         * view is on foreground, and presenter can update view now.
         */
        void onResume();

        /**
         * view is going to be covered by other UI, save some information if needed.
         */
        void onPause();

        /**
         * stop some timer or thread job for UI, and unbind IBaseView from IBasePresenter.
         */
        void onStop();

        /**
         * save some information into 'savedInfo' for showing next time if needed.
         * such as filter information, process percentage, and so on.
         * @param savedInfo passing from activity
         */
        void onSaveState(Bundle savedInfo);

        /**
         * stop some timer or thread job for all.
         * release all memory which are no need to be kept on.
         */
        void onDestroy();

        /**
         * context is not available, and do left finalization
         */
        void onDetach();

        /**
         * the listener for user saving operation
         * @param one the data that user wants to save, whose class type must be one BaseModel's T type
         * @param isNew true for adding and false fo modifying
         * @return true for request sent successfully, or else false
         */
        boolean onSaveListener(Object one, boolean isNew);

        /**
         * the listener for user deleting operation
         * @param one the data that user wants to delete, whose class type must be one BaseModel's T type
         *            Most of time it's only with a valid ID part.
         * @return true for request sent successfully, or else false
         */
        boolean onDeleteListener(Object one);
    }

    public interface IBaseView<UiType, WarningType> {
        /**
         * To do UI updating according to UiType(Enum)
         * @param uiType defined according to business requirement, such as data list, notification, status, and so on.
         */
        void doUpdateUI(UiType uiType);

        /**
         * notify UI to show some warning according to WarningType(Enum)
         * @param warnType defined according to business requirement, such as 'no access', 'need password', 'network error', and so on.
         */
        void notifyWarning(WarningType warnType);
    }
}
