package fr.axzial.paymybuddy.mapper.transaction;

import fr.axzial.paymybuddy.dto.transaction.TransactionDTO;
import fr.axzial.paymybuddy.mapper.IMapper;
import fr.axzial.paymybuddy.model.transaction.Transaction;
import org.mapstruct.Mapper;

import java.sql.Timestamp;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends IMapper<Transaction, TransactionDTO> {

    default String map(Timestamp timestamp) {
        return timestamp != null ? timestamp.toString() : null;
    }

    default Timestamp map(String timestamp) {
        return Timestamp.valueOf(timestamp);
    }

}
