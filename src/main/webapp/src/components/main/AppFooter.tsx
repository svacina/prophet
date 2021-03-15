import React from "react";
import {BottomNavigation, BottomNavigationAction} from "@material-ui/core";
import RestoreIcon from '@material-ui/icons/Restore';
import FavoriteIcon from '@material-ui/icons/Favorite';
import LocationOnIcon from '@material-ui/icons/LocationOn';
import FolderIcon from '@material-ui/icons/Folder';
import prophetStyles from "../../prophetStyles";

/**
 *
 * @constructor
 *
 */
const AppFooter = () => {

    const [value, setValue] = React.useState('recents');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const classes = prophetStyles();

    return (
        <React.Fragment>
            <BottomNavigation value={value} onChange={handleChange}>
                {/*<BottomNavigationAction label="Recents" value="recents" icon={<RestoreIcon />} />*/}
                {/*<BottomNavigationAction label="Favorites" value="favorites" icon={<FavoriteIcon />} />*/}
                {/*<BottomNavigationAction label="Nearby" value="nearby" icon={<LocationOnIcon />} />*/}
                {/*<BottomNavigationAction label="Folder" value="folder" icon={<FolderIcon />} />*/}
            </BottomNavigation>
        </React.Fragment>
    )
}
export default AppFooter;
