import React, {useState} from 'react';
import {useValidState} from "react-valid-state";
import {StateValidator} from "react-valid-state/dist/esm/validator/StateValidator";
import {WebUser} from "@model/user/webuser.model";
import AppModal from "@component/modal/AppModal";
import {MdOutlineAccountCircle, MdOutlineMoney} from "react-icons/md";
import Button from "@component/button/Button";
import {TransactionType} from "@model/transaction/transaction.model";
import {toast} from "react-toastify";

interface AddFundsModalProps {
    isOpen: boolean,
    close: () => void
    submitSubTransaction: (fundsAmount: number) => void
    user?: WebUser
}

const AddFundsModal = (props: AddFundsModalProps) => {

    const [amount, setAmount] = useState<number>(0.00);

    if (!props.user) return null;

    const submitForm = () => {
        props.submitSubTransaction(amount)
    }

    return (
        <AppModal close={props.close} isOpen={props.isOpen}>
            <form onSubmit={e => e.preventDefault()} className={"shadow-2xl bg-neutral-700 w-3/12 md:w-10/12 rounded-md flex flex-col place-items-center justify-between px-[3vw] py-[5vh]"}>
                <p className={"text-4xl mb-[5vh] font-sans text-white"}>Sub</p>

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

                <div className={"w-full flex flex-col justify-center place-items-center gap-3"}>
                    <Button type={"submit"} className={"w-4/6"} onClick={() => submitForm()}>
                        <p className={"font-sans text-white text-lg px-14"}>Drop Funds</p>
                    </Button>

                </div>
            </form>
        </AppModal>
    );
};

export default AddFundsModal;
