package globant.equattrocchio.cleanarchitecture;

import android.widget.Button;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.BuildConfig;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class PresenterTest {

    private ImagesPresenter presenter;
    private MainActivity activity;
    @Mock GetLatestImagesUseCase model;
    @Mock ImagesView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ImagesPresenter(view, model);
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void showResultOnScreen() throws Exception {
        Button button = activity.findViewById(R.id.btn_call_service);
        TextView results = activity.findViewById(R.id.tv_incoming_json);

        button.performClick();
        assertThat(results.getText().toString()).isNotEmpty();
    }

}