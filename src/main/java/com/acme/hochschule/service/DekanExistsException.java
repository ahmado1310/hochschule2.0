package com.acme.hochschule.service;

import lombok.Getter;

/**
 * Exception, falls der Dekan bereits existiert.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 */
@Getter
public class DekanExistsException extends RuntimeException{
    /**
     * Bereits vorhandener Dekan.
     */
    private final String dekan;

    DekanExistsException(final String dekan) {
        super(STR."Der Dekan \{dekan} ist ein Dekan von andere Fakultaet");
        this.dekan = dekan;
    }
}
