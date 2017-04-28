package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * validator class (implements validator) to check that a credit card number is valid
 * @author Pablo Quinoa
 */
@FacesValidator("creditCardNumberValidator")
public class creditCardNumberValidator implements Validator {

    /**
    * overrides the validate method from validator, setting a pattern to check if the credit card number follows a specific validation
    */
    @Override
    public void validate(FacesContext facesContext,
            UIComponent uIComponent, Object value) throws
            ValidatorException {
        Pattern pattern;
        pattern = Pattern.compile("(\\d{16})?");
        Matcher matcher = pattern.matcher(
                (CharSequence) value);
        HtmlInputText htmlInputText =
                (HtmlInputText) uIComponent;
        String label;

        if (htmlInputText.getLabel() == null
                || htmlInputText.getLabel().trim().equals("")) {
            label = htmlInputText.getId();
        } else {
            label = htmlInputText.getLabel();
        }

        if (!matcher.matches()) {
            FacesMessage facesMessage =
                    new FacesMessage(" is not a valid credit card number, write a 16 digit valid number");

            throw new ValidatorException(facesMessage);
        }
    }
}
