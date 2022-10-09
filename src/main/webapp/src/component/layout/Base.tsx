import React from 'react';
import NavBar from "@component/nav/NavBar";

const Base = (props: {children: React.ReactNode}) => {

    return (
        <div className={"base w-screen h-full"}>
            <NavBar/>
            <div className={"base-content-wrapper"}>
                {props.children}
            </div>
        </div>
    );

};

export default Base;