package my.edu.tarc.lab43sqlite.Model;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import my.edu.tarc.lab43sqlite.R;

/**
 * Created by TAR UC on 7/28/2017.
 */

public class UserRecordAdapter extends ArrayAdapter<UserRecord> {
    public UserRecordAdapter(Activity context, int resource, List<UserRecord> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UserRecord userRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.user_record,
                            parent,
                            false);
        }

        TextView textViewPhone, textViewName, textViewEmail;

        textViewPhone = (TextView) convertView.findViewById(R.id.textViewPhone);
        textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);


        textViewPhone.setText(textViewPhone.getText() + " : " + userRecord.getPhone());
        textViewName.setText(userRecord.getName());
        textViewEmail.setText(textViewEmail.getText() + " : " + userRecord.getEmail());


        return convertView;

    }
}
