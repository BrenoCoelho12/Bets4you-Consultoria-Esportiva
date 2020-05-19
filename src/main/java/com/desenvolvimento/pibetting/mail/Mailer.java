package com.desenvolvimento.pibetting.mail;

import com.desenvolvimento.pibetting.model.TokenValidation;
import com.desenvolvimento.pibetting.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Component
//@PropertySource({ "classpath:env/${ambiente:local}/mail-${ambiente:local}.properties" })
public class Mailer {

    @Autowired
    private Environment env;

    private static Logger logger = LoggerFactory.getLogger(Mailer.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine thymeleaf;

    @Async
    public void confirmacaoEmail(Usuario usuario, String token, HttpServletRequest request){ //aula 24.6
        String url = request.getRequestURL().toString().replaceFirst(request.getRequestURI(), "") + "/pibetting/usuario/email/confirmRegistration?token=" + token;
        Context context = new Context();
       context.setVariable("usuario", usuario);
       context.setVariable("url", url);
       context.setVariable("logo", "logo");

        try {
            String email = thymeleaf.process("mail/confirmacao_email", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); //true porque irei adicionar imagens

            helper.setFrom("breno3@ufrn.edu.br");
            helper.setTo(usuario.getEmail());
            helper.setSubject("Bets4you - confirmacao de email");
            helper.setText(email, true);

            helper.addInline("logo", new ClassPathResource("static/images/logoG.png"));

            mailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            logger.error("Erro enviando e-mail", e);
        }

    }

    @Async
    public void emailResetPassword(Usuario usuario, String token, HttpServletRequest request){

        String url = request.getRequestURL().toString().replaceFirst(request.getRequestURI(), "") + "/pibetting/usuario/email/confirmPassword?token=" + token;
        Context context = new Context();
        context.setVariable("usuario", usuario);
        context.setVariable("url", url);
        context.setVariable("logo", "logo");

        try {
            String email = thymeleaf.process("mail/confirmacao_senha", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); //true porque irei adicionar imagens

            helper.setFrom("breno3@ufrn.edu.br");
            helper.setTo(usuario.getEmail());
            helper.setSubject("Bets4you - Nova Senha");
            helper.setText(email, true);

            helper.addInline("logo", new ClassPathResource("static/images/logoG.png"));

            mailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            logger.error("Erro enviando e-mail", e);
        }

    }


}
