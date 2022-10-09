import { Modal } from '@mui/material';
import React from 'react';

const AppModal = (props: {children?: React.ReactNode, isOpen: boolean, close: () => void}) => {

    if (!props.isOpen) return null;

    return (
        <Modal open={props.isOpen} onClose={props.close} className={"flex justify-center place-items-center"}>
            <>
                {props.children}
            </>
        </Modal>
    );

};

export default AppModal;
