package fr.axzial.paymybuddy.mapper;

public interface IMapper<M, D> {
    D toDto(M model);

    M toModel(D dto);
}
