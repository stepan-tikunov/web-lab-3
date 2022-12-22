package edu.ifmo.tikunov.web.lab3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Named(value = "form")
@ApplicationScoped
public class FormBean implements Serializable {
    private Double x = .0d;
    private Double y = .0d;
    private Double r = 2.d;

    @Inject
    private AreaCheckService area;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public String submit() {
        LocalDateTime startDate = LocalDateTime.now();
        area.check(x, y, r, startDate);
        return "success";
    }

    public void ajax() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        String xString = params.get("x");
        String yString = params.get("y");
        String rString = params.get("r");

        PrimeFaces.Ajax ajax = PrimeFaces.current().ajax();
        try {
            x = Double.parseDouble(xString);
            y = Double.parseDouble(yString);
            r = Double.parseDouble(rString);

            LocalDateTime startDate = LocalDateTime.now();
            Result result = area.check(x, y, r, startDate);

            ajax.addCallbackParam("hit", result.isHit());
            ajax.addCallbackParam("date", result.getDateString());
            ajax.addCallbackParam("executionTime", result.getExecutionTime());
        } catch (NumberFormatException | NullPointerException e) {
            ajax.addCallbackParam("error", true);
        }
    }
}
