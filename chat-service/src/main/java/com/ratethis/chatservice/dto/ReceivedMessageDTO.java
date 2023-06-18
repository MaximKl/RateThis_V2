package com.ratethis.chatservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceivedMessageDTO {

    @NotBlank(message = "Message can't be blank")
    @Length(max = 2_000, min = 1, message = "Minimum size of message is 1 symbol, maximum size - 2000 symbols")
    String body;

    @NotNull(message = "Can't be null")
    @Min(value = 1, message = "Minimum id value is 1")
    long toUserId;
}
