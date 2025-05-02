package com.example.product.utility;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * The class is a utility component responsible for providing internationalization (i18n)
 * support in the application.
 */
@Component
@RequiredArgsConstructor
public class Translator {

    private final MessageSource messageSource;

    /**
     * Translates a message identified by the specified message key to the current user's locale.
     *
     * @param messageKey The key identifying the message to be translated
     * @return The translated message in the current user's locale
     */
    public String toLocal(final String messageKey) {
        return this.messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }

    /**
     * Translates a message identified by the message key with optional message parameters to the current user's locale.
     *
     * @param messageKey The key identifying the message to be translated
     * @param args       Optional message parameters
     * @return The translated message in the current user's locale with parameter substitution
     */
    public String toLocal(final String messageKey, final Object... args) {
        return this.messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }
}

