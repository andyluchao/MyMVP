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
        // save context, so some initialization.
        void onAttach(Context context);

        // initialize presenter with saved information in Bundle.
        // start some timer or thread job for all but not UI.
        void onCreate(Bundle savedInfo);

        // bind IBaseView to IBasePresenter, and start some timer or thread job for UI.
        void onStart(IView view);

        // view is on foreground, and presenter can update view now.
        void onResume();

        // view is going to be covered by other UI, save some information if needed.
        void onPause();

        // stop some timer or thread job for UI, and unbind IBaseView from IBasePresenter.
        void onStop();

        // save some information into 'savedInfo' for showing next time if needed.
        // such as filter information, process percentage, and so on.
        void onSaveState(Bundle savedInfo);

        // stop some timer or thread job for all.
        // release all memory which are no need to be kept on.
        void onDestroy();

        // context is not available, and do left finalization
        void onDetach();

        boolean createData(Object one);

        boolean modifyData(Object one);

        boolean deleteData(Object one);
    }

    public interface IBaseView<UiType, WarningType> {
        // get UI's content, return any customer data type.
        Object getUIContent(UiType uiType);

        // notify UI that some data is updated, update UI in UI thread
        void notifyUpdateUI(UiType uiType);

        // notify UI to show some warning
        void notifyWarning(WarningType warnType);
    }
}
