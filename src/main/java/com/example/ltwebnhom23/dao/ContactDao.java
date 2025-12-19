package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.Contact;

import java.util.List;

public class ContactDao extends BaseDao {

    // 1️⃣ Thêm liên hệ (user gửi form)
    public void insertContact(Contact contact) {
        getJdbi().useHandle(handle ->
                handle.createUpdate(
                                "INSERT INTO contacts (user_id, full_name, email, message, sent_at) " +
                                        "VALUES (:userId, :fullName, :email, :message, NOW())"
                        )
                        .bindBean(contact)
                        .execute()
        );
    }

    // 2️⃣ Lấy tất cả liên hệ (dùng cho admin sau này)
    public List<Contact> getAllContacts() {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT id, user_id AS userId, full_name AS fullName, email, message, sent_at AS sentAt " +
                                        "FROM contacts ORDER BY sent_at DESC"
                        )
                        .mapToBean(Contact.class)
                        .list()
        );
    }

    // 3️⃣ Lấy liên hệ theo user (nếu cần)
    public List<Contact> getContactsByUser(int userId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT id, user_id AS userId, full_name AS fullName, email, message, sent_at AS sentAt " +
                                        "FROM contacts WHERE user_id = :uid ORDER BY sent_at DESC"
                        )
                        .bind("uid", userId)
                        .mapToBean(Contact.class)
                        .list()
        );
    }

    // 4️⃣ Lấy chi tiết 1 liên hệ (admin xem chi tiết)
    public Contact getContactById(int id) {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT id, user_id AS userId, full_name AS fullName, email, message, sent_at AS sentAt " +
                                        "FROM contacts WHERE id = :id"
                        )
                        .bind("id", id)
                        .mapToBean(Contact.class)
                        .findOne()
                        .orElse(null)
        );
    }
}
