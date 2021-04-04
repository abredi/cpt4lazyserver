package com.cpt4lazy.cpt4lazyserver.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import com.cpt4lazy.cpt4lazyserver.entity.UserRole;
import com.cpt4lazy.cpt4lazyserver.service.SequenceGeneratorService;

public class UserRoleListener extends AbstractMongoEventListener<UserRole>{
	
	private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public UserRoleListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<UserRole> event) {
    	System.out.println("converting..");
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(UserRole.SEQUENCE_NAME));
        }
    }
}
