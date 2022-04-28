import React from "react";

const artists = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Address</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.artists.map((term) => {
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                    <td>{term.address}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default artists;
