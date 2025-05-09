package com.ms.user.producers;

import com.ms.user.models.UserModel;
import jakarta.validation.constraints.Email;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ms.user.dtos.EmailDto;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer( RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publicMessageEmail(UserModel userModel){
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro Realizdo Com Sucesso!");
        emailDto.setText(userModel.getName() + ", Seja Bem vindo(a)! \nAgradecemos o seu cadastro");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }



}
