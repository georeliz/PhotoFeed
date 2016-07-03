package edu.galileo.android.photofeed.photolist.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.photofeed.PhotoFeedAppModule;
import edu.galileo.android.photofeed.domain.di.DomainModule;
import edu.galileo.android.photofeed.libs.di.LibsModule;
import edu.galileo.android.photofeed.photolist.ui.PhotoListFragment;

/**
 * Created by Gerson on 3/07/2016.
 */
@Singleton
@Component(modules = {PhotoListModule.class, DomainModule.class, LibsModule.class,PhotoFeedAppModule.class})
public interface PhotoListComponent {
    void inject(PhotoListFragment fragment);
}
