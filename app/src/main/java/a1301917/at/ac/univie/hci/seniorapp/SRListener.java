package a1301917.at.ac.univie.hci.seniorapp;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by penderiko on 31.03.16.
 */
public class SRListener implements RecognitionListener {

    private a1301917.at.ac.univie.hci.seniorapp.VoiceAssistant senior_handy;


    public SRListener(VoiceAssistant va) {
        this.senior_handy=va;
    }

    private a1301917.at.ac.univie.hci.seniorapp.DialogHandler dialogHandler;

    public a1301917.at.ac.univie.hci.seniorapp.DialogHandler getDialogHandler() {
        return dialogHandler;
    }

    public void setDialogHandler(a1301917.at.ac.univie.hci.seniorapp.DialogHandler dialogHandler) {
        this.dialogHandler = dialogHandler;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.i("Listener","ReadyForSpeech");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i("Listener", "BeginningOfSpeech");
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i("Listener","BufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        Log.i("Listener","EndOfSpeech");
    }

    @Override
    public void onError(int error) {
        senior_handy.lastResult = senior_handy.new Result(Arrays.asList(""),new float[]{0F});
        Log.e("Listener","Error" + error);
    }

    @Override
    public void onResults(Bundle results) {
        Log.i("SRListener","results ready");
        senior_handy.lastResult = senior_handy.new Result(
                results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION),
                results.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES));
        if (dialogHandler!=null)
            dialogHandler.handle(senior_handy.lastResult.msg.get(0),senior_handy);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.i("Listener","partialResult");
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.i("Listener","Event: " + eventType);
    }
}