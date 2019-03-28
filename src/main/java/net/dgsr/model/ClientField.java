package net.dgsr.model;

import lombok.Data;

@Data
public class ClientField {

    private Integer id;

    private String field_type;

    private String field_name;

    private Integer parent_id;

    private String describe;

    private String database_type;

    private Integer orderby;
}