package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.Contact;
import java.util.List;

public class ContactDao extends BaseDao {

    public void insertContact(Contact contact) {
        getJdbi().useHandle(handle ->
                handle.createUpdate(
                                "INSERT INTO contacts (user_id, full_name, email, message, sent_at) " +
                                        "VALUES (:userId, :fullName, :email, :message, NOW())"
                        )
                        .bind("userId", contact.getUser_id())
                        .bind("fullName", contact.getFull_name())
                        .bind("email", contact.getEmail())
                        .bind("message", contact.getMessage())
                        .execute()
        );
    }

    public List<Contact> getAllContacts() {
        return getJdbi().withHandle(handle ->
                handle.createQuery(

                                "SELECT id, user_id, full_name, email, message, sent_at " +
                                        "FROM contacts ORDER BY sent_at DESC"
                        )
                        .mapToBean(Contact.class)
                        .list()
        );
    }

    public List<Contact> getContactsByUser(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT id, user_id, full_name, email, message, sent_at " +
                                        "FROM contacts WHERE user_id = :uid ORDER BY sent_at DESC"
                        )
                        .bind("uid", userId)
                        .mapToBean(Contact.class)
                        .list()
        );
    }

    public Contact getContactById(int id) {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT id, user_id, full_name, email, message, sent_at " +
                                        "FROM contacts WHERE id = :id"
                        )
                        .bind("id", id)
                        .mapToBean(Contact.class)
                        .findOne()
                        .orElse(null)
        );
    }
}