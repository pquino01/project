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
 * validator class (implements validator) to check that an expiration date is valid
 * @author Pablo Quinoa
 */
@FacesValidator("monthYearValidator")
public class monthYearValidator implements Validator {

    /**
    * overrides the validate method from validator, setting a pattern to check if the expiration date follows a specific validation
    */
    @Override
    public void validate(FacesContext facesContext,
            UIComponent uIComponent, Object value) throws
            ValidatorException {
        Pattern pattern;
        String regex = "(^(0?[1-9]|1[012])[-/]?(0?[1-9]|[12][0-9]|3[01]))?";
        pattern = Pattern.compile(regex);
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
                    new FacesMessage("is not a valid expiration date, mm/dd required");
            throw new ValidatorException(facesMessage);
        }
    }
}
