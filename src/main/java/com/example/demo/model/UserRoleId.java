package com.example.demo.model;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class UserRoleId implements Serializable {
    private Long user;
    private Long role;
}
