package com.Notification.Service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCreatedEvent {

    private Long documentId;

    private String documentName;

    private String documentType;

    private String status;
}