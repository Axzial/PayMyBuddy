import React from 'react';

const Divider = (props: {text: string}) => {
    return (
        <div className="relative w-full flex flex-row justify-evenly">
            <div className="inset-0 flex items-center w-full" aria-hidden="true">
                <div className="w-full border-t border-white" />
            </div>
            <div className="flex justify-center">
                <span className="px-2 text-sm text-white">{props.text}</span>
            </div>
            <div className="inset-0 flex items-center w-full" aria-hidden="true">
                <div className="w-full border-t border-white" />
            </div>
        </div>
    );
};

export default Divider;
