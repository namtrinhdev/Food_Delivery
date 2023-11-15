package namtdph08817.android.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViMoneyActivity extends AppCompatActivity {
    private TextView tv_so_du;
    private ImageView img_sh_so_du;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi_money);
        tv_so_du = findViewById(R.id.tv_so_du_tai_khoan);
        img_sh_so_du = findViewById(R.id.img_show_hide_so_du);

        //ẩn hiện money
        img_sh_so_du.setImageResource(R.drawable.view);
        img_sh_so_du.setOnClickListener(v->{
            toggleMoneyVisibility();
        });
        tv_so_du.setTag(true);
        tv_so_du.setText(getSpannableText(true));

        //nút quay lại
        findViewById(R.id.img_backpress_wallet).setOnClickListener(v ->{
            onBackPressed();
        });
    }

    private void toggleMoneyVisibility() {
        boolean isMoneyVisible = tv_so_du.getTag() == null || (boolean) tv_so_du.getTag();
        if (isMoneyVisible) {
            tv_so_du.setTag(false);
            tv_so_du.setText(getSpannableText(false));
            img_sh_so_du.setImageResource(R.drawable.eye);
        } else {
            tv_so_du.setTag(true);
            tv_so_du.setText(getSpannableText(true));
            img_sh_so_du.setImageResource(R.drawable.view);
        }
    }
    private String getSpannableText(final boolean hideText) {
        String text = hideText ? "*****" : "50000 VND";
        return text;
    }
}