import React, {useEffect, useState} from 'react';
import useUser from "@hooks/useUser";
import Base from "@component/layout/Base";
import Button from "@component/button/Button";
import SendFundsModal from "@component/modal/SendFundsModal";
import AddFundsModal from "@component/modal/AddFundsModal";
import AppConstants from "@config/AppConstants";
import {Transaction, TransactionType} from "@model/transaction/transaction.model";
import {DataGrid, GridColDef} from "@mui/x-data-grid";
import {RootState, useAppDispatch} from "@pages/_app";
import TransactionService from "@service/transaction.service";
import {fetchSelfUser} from "@store/user/user.slice";
import {fetchTransactions} from "@store/transaction/transaction.reducer";
import {useSelector} from "react-redux";
import SubFundsModal from "@component/modal/SubFundsModal";
import {toast} from "react-toastify";
import {AxiosError} from "axios";
import {List} from "immutable";
import moment from "moment";
import {Avatar} from "@mui/material";
import {fetchContacts} from "@store/contacts/contacts.slice";
import AddContactModal from "@component/modal/AddContactModal";

const columns: GridColDef[] = [
    {
        field: 'time',
        headerName: 'Time',
        type: 'number',
        flex: 2,
    },
    {
        field: 'receiver',
        headerName: 'Receiver',
        flex: 2,
    },
    {
        field: 'sender',
        headerName: 'Sender',
        flex: 2,
    },
    {
        field: 'amount',
        headerName: 'Amount (USD)',
        flex: 1,
    },
    {
        field: 'type',
        headerName: 'Type',
        type: 'number',
        flex: 1,
    },
];

export type GridMappedTransaction = {
    id?: string,
    time?: string,
    receiver?: string,
    sender?: string,
    amount?: number,
    type?: TransactionType
};

const ProfilePage = () => {

    const dispatch = useAppDispatch();
    const transactions = useSelector((state: RootState) => state.transactionReducer.transactions);

    const [token, setToken] = useState<string | undefined>();
    const selfUser = useUser(token);

    const [sendMoneyModal, setSendMoneyModal] = useState<boolean>(false);
    const [addMoneyModal, setAddMoneyModal] = useState<boolean>(false);
    const [subMoneyModal, setSubMoneyModal] = useState<boolean>(false);

    useEffect(() => {
        const token = localStorage.getItem(AppConstants.LOCAL_STORAGE_TOKEN_KEY);
        if (token) {
            setToken(token);
            dispatch(fetchSelfUser(token ?? ''));
            dispatch(fetchTransactions(token ?? ''));
            dispatch(fetchContacts(token ?? ''))
        }
    }, []);

    const handleClickSendMoney = () => setSendMoneyModal(!sendMoneyModal);
    const handleClickAddMoney = () => setAddMoneyModal(!addMoneyModal);
    const handleClickSubMoney = () => setSubMoneyModal(!subMoneyModal);

    const submitTransaction = (fundsAmount: number, receiver: string, type: TransactionType = TransactionType.PAYMENT) => {
        TransactionService.postTransaction(token ?? '', {
            amountUsd: fundsAmount,
            transactionType: type,
            receiver
        })
            .catch((error: AxiosError) => toast.error("Error while processing this transaction : " + error.code))
            .then(() => toast.info("Transaction Succeeded"))
            .then(() => {
                dispatch(fetchSelfUser(token ?? ''));
                dispatch(fetchTransactions(token ?? ''));
            });
    }

    const submitSendMoneyTransaction = (fundsAmount: number, receiver: string) => {
        submitTransaction(fundsAmount, receiver, TransactionType.PAYMENT);
        handleClickSendMoney()
    }

    const submitAddMoneyTransaction = (fundsAmount: number) => {
        submitTransaction(fundsAmount, selfUser?.uuid ?? "", TransactionType.CASH_IN);
        handleClickAddMoney()
    }

    const submitSubMoneyTransaction = (fundsAmount: number) => {
        submitTransaction(fundsAmount, selfUser?.uuid ?? "", TransactionType.CASH_OUT)
        handleClickSubMoney()
    }

    const mapTransactions = (toMapTransactions: List<Transaction>): GridMappedTransaction[] => {
        if (toMapTransactions.isEmpty()) return [];
        return toMapTransactions.sort(compareTransactions).map((transaction: Transaction) => {
            return {
                id: transaction.uuid,
                time: !!transaction.at ? moment(transaction.at).format('MMMM Do YYYY, h:mm:ss a') : "Not Defined",
                receiver: transaction.webUserReceiver?.email,
                sender: transaction.webUserInitiator?.email,
                amount: transaction.amountUsd,
                type: transaction.transactionType
            }
        }).toArray();
    }

    const compareTransactions = (a: Transaction, b: Transaction) => {
        console.log(a)
        return moment(a.at).isBefore(moment(b.at)) ? 1 : -1;
    }

    return (
        <Base>
            <div className={"w-full min-h-screen bg-neutral-700 flex justify-center place-items-center py-[8vh]"}>
                <div className={"flex flex-col justify-center place-items-center gap-10 mt-[6vh]"}>
                    <div hidden={!selfUser} className={"flex flex-col justify-center place-items-center gap-2"}>
                        <p className={"font-sans text-4xl text-white"}>Hello {selfUser?.name}</p>
                        <p className={"font-sans text-3xl text-white"}>Your balance
                            is {Intl.NumberFormat('en-US').format(selfUser?.walletUsd ?? 0)} USD</p>
                    </div>
                    <div className={"flex flex-row justify-center place-items-center gap-10"}>
                        <Button onClick={handleClickSendMoney}>
                            <p>Send Money</p>
                        </Button>
                        <Button onClick={handleClickAddMoney}>
                            <p>Add Money</p>
                        </Button>
                        <Button onClick={handleClickSubMoney}>
                            <p>Drop Money</p>
                        </Button>
                    </div>
                    <div className={"w-[80vw] h-[50vh] lg:w-[90vw] xl:w-[80vw] flex flex-col gap-5"}>
                        <h1 className={"text-white text-2xl"}>Transactions</h1>
                        <DataGrid style={{color: "white"}} columns={columns}
                                  rows={mapTransactions(List.of(...transactions))}/>
                    </div>
                </div>
            </div>
            <SendFundsModal
                isOpen={sendMoneyModal}
                close={handleClickSendMoney}
                submitSendTransaction={submitSendMoneyTransaction}
                user={selfUser}
            />
            <AddFundsModal
                isOpen={addMoneyModal}
                close={handleClickAddMoney}
                submitAddTransaction={submitAddMoneyTransaction}
                user={selfUser}
            />
            <SubFundsModal
                isOpen={subMoneyModal}
                close={handleClickSubMoney}
                submitSubTransaction={submitSubMoneyTransaction}
                user={selfUser}
            />
        </Base>
    );
};

export default ProfilePage;
