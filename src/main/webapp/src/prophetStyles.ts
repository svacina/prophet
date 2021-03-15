import {makeStyles} from "@material-ui/core";
import prophetTheme from "./prophetTheme";

//import theme
const theme = prophetTheme;

/**
 * Create global styles for application
 */
const prophetStyles = makeStyles({
    root: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    main: {
        marginTop: theme.spacing(8),
        marginBottom: theme.spacing(2),
    },
    footer: {
        // padding: theme.spacing(3, 2),
        marginTop: 'auto',
        backgroundColor:
            theme.palette.type === 'light' ? theme.palette.grey[200] : theme.palette.grey[800],
    },

    content: {
        flexGrow: 1,
        padding: theme.spacing(8, 0, 6),
        backgroundColor: theme.palette.background.default
    },
    box: {
        border: 0,
        borderRadius: 3,
        boxShadow: '0 3px 5px 2px',
        padding: '12px 30px',
    },
    appBar: {
        backgroundColor: theme.palette.primary.main,
        zIndex:1
    },
    overlay: {
        position: 'fixed',
        width: '100%',
        height: '100%',
        background: 'rgba(0,0,0,0.3)',
        display: 'flexbox',
        flexAlign: 'center',
        flexPack: 'center',
        zIndex:2
    },
    boxes_root: {
        flexGrow: 1,
        margin: theme.spacing(2),
    },
    boxes_paper: {
        padding: theme.spacing(2),
        marginTop: theme.spacing(2),
        marginBottom: theme.spacing(2),
    },
    inconsistency_box: {
        margin: theme.spacing(2),
    },
    entity_item: {
        padding: theme.spacing(2)
    }

});

export default prophetStyles;
