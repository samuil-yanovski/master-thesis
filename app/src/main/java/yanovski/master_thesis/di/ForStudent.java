package yanovski.master_thesis.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Samuil on 12/30/2015.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ForStudent {
}
