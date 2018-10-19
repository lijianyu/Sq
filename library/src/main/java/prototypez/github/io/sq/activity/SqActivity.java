package prototypez.github.io.sq.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import prototypez.github.io.sq.R;
import prototypez.github.io.sq.Sq;

public abstract class SqActivity extends AppCompatActivity {

    FrameLayout sequenceContainer;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sequenceContainer = new FrameLayout(this);
        sequenceContainer.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        sequenceContainer.setId(R.id.fragment_container);
        setContentView(sequenceContainer);

        fragmentManager = getSupportFragmentManager();
    }


    protected void push(Fragment fragment) {
        Sq.push(this, fragment);
    }

    public  <T extends Fragment> T findOrCreateFragment(@NonNull Class<T> fragmentClass) {
        return Sq.findOrCreateFragment(fragmentClass, this, fragmentClass.getCanonicalName());
    }
}
