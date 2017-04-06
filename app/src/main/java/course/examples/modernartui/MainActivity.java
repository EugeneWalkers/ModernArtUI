package course.examples.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static DialogFragment dialogFragment;
    ArrayList<View> al = new ArrayList<>();
    ArrayList<Integer>reds = new ArrayList<>();
    ArrayList<Integer>greens = new ArrayList<>();
    ArrayList<Integer>blues = new ArrayList<>();
    static private final String MOMA = "http://www.moma.org";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        al.add((View)findViewById(R.id.view1));
        al.add((View)findViewById(R.id.view2));
        al.add((View)findViewById(R.id.view3));
        al.add((View)findViewById(R.id.view4));
        al.add((View)findViewById(R.id.view5));
        al.add((View)findViewById(R.id.view6));
        for (int i=0; i<al.size(); i++){
            reds.add(new Random().nextInt()%20+30);
            greens.add(new Random().nextInt()%30+30);
            blues.add(new Random().nextInt()%40+30);
            al.get(i).setBackgroundColor(Color.rgb(reds.get(i), greens.get(i), blues.get(i)));
        }

        SeekBar sb = (SeekBar)findViewById(R.id.seekBar);


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (int i=0; i<al.size(); i++){
                    al.get(i).setBackgroundColor(Color.rgb(reds.get(i)+progress,
                            greens.get(i)+progress,
                            blues.get(i)+progress)+progress/2);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.about:
                dialogFragment = new AlertDialogFragment();
                dialogFragment.show(getFragmentManager(), "Alert");
                break;
        }
        return true;
    }

    public static class AlertDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("The site of The Museum of Modern Art site - moma.org\nDo you want to visit it?");
            builder.setCancelable(true);
            builder.setPositiveButton("Go to site",  new DialogInterface.OnClickListener() {
                public void onClick(
                        final DialogInterface dialog, int id) {
                        Uri adr = Uri.parse(MOMA);
                        Intent intent = new Intent(Intent.ACTION_VIEW, adr);
                        Intent chooser = Intent.createChooser(intent, "Select app:");
                        startActivity(chooser);
                }
            });
            builder.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
                public void onClick(
                        final DialogInterface dialog, int id) {
                        dialogFragment.dismiss();
                }
            });
            return builder.create();
        }
    }

}
