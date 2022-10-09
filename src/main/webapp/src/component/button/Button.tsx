import React, {MouseEventHandler} from 'react';
import Link from "next/link";

export type TWSize = "sm" | "md" | "lg";

interface ButtonParams {
    children?: React.ReactNode;
    href?: string;
    type?: "submit" | "reset" | "button";
    onClick?: MouseEventHandler<any>;
    className?: string;
    disabled?: boolean;
}

const Button = (props: ButtonParams) => {

    return (
        <>
            {props.href ?
                <Link href={props.href} passHref={true}>
                    <button
                        disabled={props.disabled ?? false}
                        type={props.type ?? "button"}
                        className={`inline-flex items-center justify-center px-2.5 py-1.5 font-medium rounded shadow-sm text-white bg-green-300 hover:scale-105 transition duration-400 ${props.className}`}
                        onClick={props.onClick}
                    >
                        {props.children}
                    </button>
                </Link>
                :
                <button
                    disabled={props.disabled ?? false}
                    type={props.type ?? "button"}
                    className={`inline-flex items-center justify-center px-2.5 py-1.5 font-medium rounded shadow-sm text-white bg-green-300 hover:scale-105 transition duration-400 ${props.className}`}
                    onClick={props.onClick}
                >
                    {props.children}
                </button>
            }
        </>
    );
};

export default Button;
