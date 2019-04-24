package com.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.model.Employee;
import com.model.Task;

import java.text.SimpleDateFormat;
import java.util.Properties;

public class SendMail {

	public String sendMailForRegister(String to, Employee emp, String password) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ofcadmncog@gmail.com", "Admin@9454");// change
																						// accordingly
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ofcadmncog@gmail.com"));// change
																			// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Welcome to e-Office Suite " + emp.getEmpName());
			message.setText("Welcome to on-Board into E-office Corp.\nWelcome " + emp.getEmpName()
					+ ", your details as below:-\nLoginid: " + emp.getEmpEmailID() + "\nPassword: " + password
					+ "\n\n\nRegards.\nAdmin Team.\nE-Office Corp.");

			// send message
			Transport.send(message);

			System.out.println("Message Sent Successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		props.clear();
		return "success";
	}

	public String sendMailForTaskAssign(String to, Task task, String userName) {
		Properties props1 = new Properties();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		String date = sd.format(task.getStartDate());
		String eDate = sd.format(task.getEndDate());
		props1.put("mail.smtp.host", "smtp.gmail.com");
		props1.put("mail.smtp.socketFactory.port", "465");
		props1.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props1.put("mail.smtp.auth", "true");
		props1.put("mail.smtp.port", "465");

		Session session1 = Session.getInstance(props1, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ofcadmncog@gmail.com", "Admin@9454");// change
																						// accordingly
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session1);
			message.setFrom(new InternetAddress("ofcadmncog@gmail.com"));// change
																			// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Welcome to e-Office Suite " + userName);
			message.setText("Hello " + userName + ",\nYou Have Assigned Task Below:-\n" + "Task Subject: "
					+ task.getTaskName() + "\nTask Description: " + task.getTaskDescription() + "\n"
					+ "Task Start Date: " + date + "\n" + "Task End Date: " + eDate
					+ "\n\n\n" + "Regards.\nAdmin Team.\nE-Office Corp.");

			// send message
			Transport.send(message);

			System.out.println("Message Sent Successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return "success";
	}

	public String sendMailForTaskStatus(Task task, String userName) {
        Properties props2 = new Properties();
        props2.put("mail.smtp.host", "smtp.gmail.com");
        props2.put("mail.smtp.socketFactory.port", "465");
        props2.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props2.put("mail.smtp.auth", "true");
        props2.put("mail.smtp.port", "465");

        Session session2 = Session.getInstance(props2, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("slashinvester@gmail.com", "Priyank@9377");// change
                // accordingly
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session2);
            message.setFrom(new InternetAddress("ofcadmncog@gmail.com"));// change
            // accordingly
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("ofcadmncog@gmail.com"));
            message.setSubject("Task Status of " + userName);
            message.setText("Hello Admin,\nChange Status of Employee "+userName+":-\n\n"+ "TASK STATUS: "+ task.getStatus().toUpperCase() + "\nTask Subject: "
                    + task.getTaskName() + "\nTask Description: " + task.getTaskDescription() + "\n"
                    + "Task Start Date: " + task.getStartDate() + "\n" + "Task End Date: " + task.getEndDate()
                    + "\n\n\n" + "Regards.\nAdmin Team.\nE-Office Corp.");

            // send message
            Transport.send(message);

            System.out.println("Message Sent Successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }


}
