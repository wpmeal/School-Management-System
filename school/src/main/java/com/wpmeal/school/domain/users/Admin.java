package com.wpmeal.school.domain.users;


import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName="uId")

public class Admin extends User {
    
}
