package com.example.start.l6;

        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.app.NotificationCompat;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.RemoteViews;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button toastButton;
    Button dialogButton;
    Button powiadomienieButton;
    Button npowiadomienieButton;
    int mNotificationCount = 0;

    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    private Uri soundURI = Uri.parse("android.resource://com.example.start.l6/" + R.raw.woof);
    private long[] vibrationPattern = {0, 200, 200, 300};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastButton = (Button) findViewById(R.id.toastButton);
        toastButton.setOnClickListener(toastButtonListener);

        dialogButton = (Button) findViewById(R.id.dialogButton);
        dialogButton.setOnClickListener(dialogButtonListener);

        powiadomienieButton = (Button) findViewById(R.id.powiadomienieButton);
        powiadomienieButton.setOnClickListener(powiadomienieButtonListener);

        npowiadomienieButton = (Button) findViewById(R.id.nPowiadomienieButton);
        npowiadomienieButton.setOnClickListener(npowiadomienieButtonListener);




        mNotificationIntent = new Intent(getApplicationContext(), IntentActivity.class);

        mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0, mNotificationIntent, 0);
    }


    public View.OnClickListener toastButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), "toast", Toast.LENGTH_SHORT).show();
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.AXIS_PULL_BEFORE, 10, -10);

            View toastView = getLayoutInflater().inflate(R.layout.toast, null);
            TextView text = (TextView) toastView.findViewById(R.id.textView2);
            text.setText("wow much customization!");

            toast.setView(toastView);

            toast.show();
        }
    };
    public View.OnClickListener dialogButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ExampleDialog dialog = new ExampleDialog();
            dialog.show(getFragmentManager(), "dialog");//menedżer fragmentów oraz nazwa dialog boxa
        }
    };
    public View.OnClickListener powiadomienieButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "toast", Toast.LENGTH_SHORT).show();
            String contextTitle = "Wow";
            String contentText = "uszanowanko";
            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(view.getContext())
                    .setSmallIcon(android.R.drawable.stat_sys_warning)
                    .setContentTitle(contextTitle)
                    .setContentText(contentText);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(view.getContext().NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    };
    public View.OnClickListener npowiadomienieButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String contentText = "wow";
            String ticketText = "wowowwo";
            Toast.makeText(view.getContext(), "toast", Toast.LENGTH_SHORT).show();
//            RemoteViews mContentView = new RemoteViews("com.example.start.l6", R.layout.custom_notification);
            RemoteViews mContentView = new RemoteViews("com.example.start.l6", R.layout.custom_notification);
            mContentView.setTextViewText(R.id.textView, contentText + "(" + ++mNotificationCount + ")");
//Tworzymy powiadomienie
            NotificationCompat.Builder notificationBuilder = new
                    NotificationCompat.Builder(getApplicationContext());
            notificationBuilder.setTicker(ticketText);//ticker tekst, powyzej android
//        5.0 nie działa
            notificationBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);//mal
//        a ikona na pasku powiadomien
            notificationBuilder.setAutoCancel(true);//automatyczne usunięcie
//        powiadomienia jak użytkownik wyciągnie szuflade
            notificationBuilder.setContentIntent(mContentIntent);//akcja gdy klikniemy
//        w szufladzie powiadomienie
            notificationBuilder.setSound(soundURI);//dzwięk powiadomienia
            notificationBuilder.setVibrate(vibrationPattern);//patern wibracji
            notificationBuilder.setContent(mContentView);//wygląd
        }
    };
}
