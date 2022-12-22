package edu.ifmo.tikunov.web.lab3;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("formValidator")
public class FormValidator implements Validator {

    private void error(String summary) throws ValidatorException {
        FacesMessage message = new FacesMessage(summary);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }

    protected void validateX(Double x) throws ValidatorException {
        if (x < -4.d || x > 4.d) {
            error("Должно быть от -4 до 4");
        }
    }

    protected void validateY(Double y) throws ValidatorException {
        if (y < -5.d || y > 5.d) {
            error("Должно быть от -3 до 3");
        }
    }

    protected void validateR(Double r) throws ValidatorException {
        if (r < 2.d || r > 5.d) {
            error("Должно быть от 1 до 5");
        }
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            error("Введите значение");
        }
        switch(uiComponent.getId()) {
            case "x":
                validateX((Double) o);
                break;
            case "y":
                validateY((Double) o);
                break;
            case "r":
                validateR((Double) o);
        }
    }
}
