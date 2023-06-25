package br.com.muniz.aws_project01.model;

import br.com.muniz.aws_project01.enums.EventTypeEnumm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Envelope {

    private EventTypeEnumm eventType;
    private String data;

}
