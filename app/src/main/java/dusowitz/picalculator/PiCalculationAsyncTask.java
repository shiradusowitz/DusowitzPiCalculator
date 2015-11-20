package dusowitz.picalculator;

import android.os.AsyncTask;
import android.widget.TextView;

public class PiCalculationAsyncTask extends AsyncTask<Long, String, String> {

    private TextView textView;

    public PiCalculationAsyncTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Long... params) {

        double pi = calculate(100000000000000L);
        return String.valueOf(pi);

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        textView.setText(values[0]);


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        textView.setText(result);
    }

    public double calculate(long l) {
        double pi = 0;

        double numerator = 4;
        double denominator = 1;
        boolean positive = true;

        for (long i = 0; i < l; i++) {
            if (positive) {
                pi += numerator / denominator;
            } else {
                pi -= numerator / denominator;
            }

            positive = !positive;
            denominator += 2;

            if ( i % 10000L == 0 ) {
                publishProgress(String.valueOf(pi));
            }
        }

        return pi;
    }


}