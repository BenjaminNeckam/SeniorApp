package a1301917.at.ac.univie.hci.seniorapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

/**
 * Created by penderiko on 17.04.16.
 */
public class VoiceAssistant {

    public class Result {
        public Result(List<String> msg, float[] certainty) {
            this.msg = msg;
            this.certainty = certainty;
        }

        public List<String> msg;
        public float[] certainty;
    }

    private Intent SRIntent;
    private TextToSpeech tts;
    private a1301917.at.ac.univie.hci.seniorapp.SRListener listener;
    private SpeechRecognizer sro;
    private Context applicationContext;


    private boolean paused;
    Result lastResult;




    public boolean ttsReady;
    ConditionVariable resultReady;

    public VoiceAssistant(Activity activity) {
        this.applicationContext = activity.getApplicationContext();

        if (!SpeechRecognizer.isRecognitionAvailable(applicationContext))
            throw new RuntimeException("Fehlermeldung ist aussagekräftig!!!");
        SRIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        SRIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        SRIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de-AT");
        SRIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,  activity.getApplication().getPackageName());



        //resultReady = new ConditionVariable();

        listener = new SRListener(this);
        paused = true;

        resume();
    }

    public void pause() {
        sro.destroy();

        tts.stop();
        tts.shutdown();
        ttsReady = false;

        paused = true;
    }

    public void resume() {
        if (!paused) return;
        sro = SpeechRecognizer.createSpeechRecognizer(applicationContext);
        sro.setRecognitionListener(listener);
        tts = new TextToSpeech(applicationContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    ttsReady = true;
                }
            }
        });
        paused = false;
    }

    private Thread runner;

    public void start() {

    }

    public void dialog(String iSaid, final DialogHandler dialogHandler) {

        listener.setDialogHandler(dialogHandler);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int rc = tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                    Log.i("VoiceAssistant", "Start talking");
                }

                @Override
                public void onDone(String utteranceId) {
                    Log.i("VoiceAssistant", "Done talking");
                    if (dialogHandler == null) return;
                    Handler handler = new Handler(applicationContext.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            VoiceAssistant.this.sro.startListening(SRIntent);
                        }
                    });
                }

                @Override
                public void onError(String utteranceId) {
                    Log.e("VoiceAssistant", "Error");
                }
            });
            if (rc== TextToSpeech.SUCCESS) {
                Log.i("hi", "hi");
                tts.speak(iSaid, TextToSpeech.QUEUE_FLUSH, null, "MESSAGE");

            } else
                Log.e("VoiceAssistant","Error: " + rc);
        } else {
            int rc = tts.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
                @Override
                public void onUtteranceCompleted(String utteranceId) {
                    Log.i("VoiceAssistant", "Done talking");
                    Handler handler = new Handler(applicationContext.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            VoiceAssistant.this.sro.startListening(SRIntent);
                        }
                    });
                }
            });
            if (rc== TextToSpeech.SUCCESS) {
                Log.i("hi", "ho");
                HashMap<String,String> params = new HashMap<>();
                params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"MESSAGE");
                tts.speak(iSaid, TextToSpeech.QUEUE_FLUSH, params);

            } else
                Log.e("VoiceAssistant","Error: " + rc);

        }


    }

    public void say(String utterance) {
        dialog(utterance, null);
    }

    public String what() {
        Log.i("VoiceAssistent", "what returns" + lastResult.msg.get(0));
        return lastResult.msg.get(0);
    }

}
