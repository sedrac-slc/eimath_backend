package com.ei.math.cls;

import com.ei.math.entity.Group;
import com.ei.math.entity.UserPeople;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class ElementId implements Serializable{
    private UserPeople userPeople;
    private Group group;       
}
