package de.ollinux.annotations

import javax.inject.Qualifier
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * <a href="mailto:oliver.vesper@googlemail.com">Oliver Vesper</a>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface First {

}
