import React, {useState} from 'react';
import {WebUser} from "@model/user/webuser.model";
import {MdOutlineAccountCircle, MdOutlineMoney} from "react-icons/md";
import Button from "@component/button/Button";
import AppModal from "@component/modal/AppModal";
import {TransactionType} from "@model/transaction/transaction.model";

interface SendFundsModalProps {
    isOpen: boolean,
    close: () => void
    submitSendTransaction: (fundsAmount: number, receiver: string, type: TransactionType) => void
    user?: WebUser
}

const SendFundsModal = (props: SendFundsModalProps) => {

    const [amount, setAmount] = useState<number>(0.00);
    const [receiver, setReceiver] = useState<string>('');

    if (!props.user) return null;

    return (
        <AppModal close={props.close} isOpen={props.isOpen}>
            <form onSubmit={e => e.preventDefault()} className={"shadow-2xl bg-neutral-700 w-3/12 md:w-10/12 rounded-md flex flex-col place-items-center justify-between px-[3vw] py-[5vh]"}>
                <p className={"text-4xl mb-[5vh] font-sans text-white"}>Login</p>

                <div className={"w-full mb-[2vh]"}>
                    <label htmlFor="email" className="block text-sm font-medium text-white">
                        Amount
                    </label>
                    <div className="mt-2 relative rounded-md shadow-sm">
                        <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <MdOutlineMoney className="h-5 w-5 text-white" aria-hidden="true"/>
                        </div>
                        <input
                            type="number"
                            step=".01"
                            className="bg-neutral-800 border-none text-white font-raleway font-medium block w-full pl-10 rounded-md hover:bg-neutral-900"
                            placeholder="80$"
                            onChange={(e) => setAmount(e.target.value as any)}
                        />
                    </div>
                </div>

                <div className={"w-full mb-[2vh]"}>
                    <label htmlFor="email" className="block text-sm font-medium text-white">
                        Receiver
                    </label>
                    <div className="mt-2 relative rounded-md shadow-sm">
                        <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <MdOutlineAccountCircle className="h-5 w-5 text-white" aria-hidden="true"/>
                        </div>
                        <input
                            type="text"
                            className="bg-neutral-800 border-none text-white font-raleway font-medium block w-full pl-10 rounded-md hover:bg-neutral-900"
                            placeholder="test@account.com"
                            onChange={(e) => setReceiver(e.target.value)}
                        />
                    </div>
                </div>

                <div className={"w-full flex flex-col justify-center place-items-center gap-3"}>
                    <Button type={"submit"} className={"w-4/6"} onClick={() => props.submitSendTransaction(amount, receiver, TransactionType.PAYMENT)}>
                        <p className={"font-sans text-white text-lg px-14"}>Make Payment</p>
                    </Button>

                </div>
            </form>
        </AppModal>
    );
};

export default SendFundsModal;
