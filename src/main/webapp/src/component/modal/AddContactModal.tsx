import React, {useState} from 'react';
import AppModal from "@component/modal/AppModal";
import {MdContactMail} from "react-icons/md";
import Button from "@component/button/Button";

interface AddContactModalProps {
    isOpen: boolean,
    close: () => void
    submitAddContact: (uuid: string) => void
}

const AddFundsModal = (props: AddContactModalProps) => {

    const [contact, setContact] = useState<string | undefined>(undefined);

    const submitForm = () => {
        if (contact) props.submitAddContact(contact);
    }

    return (
        <AppModal close={props.close} isOpen={props.isOpen}>
            <form onSubmit={e => e.preventDefault()}
                  className={"shadow-2xl bg-neutral-700 w-3/12 md:w-10/12 rounded-md flex flex-col place-items-center justify-between px-[3vw] py-[5vh]"}>
                <p className={"text-4xl mb-[5vh] font-sans text-white"}>Add</p>

                <div className={"w-full mb-[2vh]"}>
                    <label htmlFor="email" className="block text-sm font-medium text-white">
                        Amount
                    </label>
                    <div className="mt-2 relative rounded-md shadow-sm">
                        <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <MdContactMail className="h-5 w-5 text-white" aria-hidden="true"/>
                        </div>
                        <input
                            className="bg-neutral-800 border-none text-white font-raleway font-medium block w-full pl-10 rounded-md hover:bg-neutral-900"
                            placeholder="axzial@protonmail.com"
                            onChange={(e) => setContact(e.target.value)}
                        />
                    </div>
                </div>

                <div className={"w-full flex flex-col justify-center place-items-center gap-3"}>
                    <Button disabled={!contact} type={"submit"} className={"w-4/6"} onClick={() => submitForm()}>
                        <p className={"font-sans text-white text-lg px-14"}>Add Contact</p>
                    </Button>

                </div>
            </form>
        </AppModal>
    );
};

export default AddFundsModal;
