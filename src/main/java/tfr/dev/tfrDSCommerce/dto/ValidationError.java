package tfr.dev.tfrDSCommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends  CustomErrorDTO{

    private List<FieldMessage> erros = new ArrayList<>();


    public ValidationError(Instant timeStamp, Integer status, String error, String path) {
        super(timeStamp, status, error, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addErros(String fieldName, String fieldMessage) {
        erros.add(new FieldMessage(fieldName,fieldMessage));
    }
}
