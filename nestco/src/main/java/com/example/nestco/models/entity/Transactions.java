//package com.example.nestco.models.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.CurrentTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.awt.*;
//import java.sql.Timestamp;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Transactions {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="transaction_id")
//    private Long id;                    // 거래 고유 Id
//
//    @ManyToOne
//    @JoinColumn(name = "requester_id")
//    private Member requester;        // 요청자 Id
//
//    @ManyToOne
//    @JoinColumn(name = "receiver_id")
//    private Member receiver;         // 수신자 Id
//
//
//    @ManyToOne
//    @JoinColumn(name = "item_id")
//    private NestcoItems item;        // 교환신청물품(수신자물품)
//
//    @Column
//    private NestcoItems exchange_item_id;   // 교환물품(요청자물품)
//
//    @Column
//    private boolean status;         // 거래 상태(대기중, 완료, 취소)
//
//    @Column(length = 1000)
//    private String message;       // 거래시 남긴 메세지
//
//    @CreationTimestamp
//    @Column(updatable = false)
//    private Timestamp created_At;   // 거래요청시간
//
//    @UpdateTimestamp
//    @Column(insertable = false)
//    private Timestamp update_At;    // 거래상태변경시간
//
//
//    // 거래 상태 변경 메서드
//    public void updateTransactionStatus(boolean newStatus) {
//        this.status = newStatus;
//        this.item.setStatus(newStatus);  // NestcoItems의 상태도 동기화
//    }
//}