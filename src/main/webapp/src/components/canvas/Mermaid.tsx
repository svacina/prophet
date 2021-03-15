import React from "react";
import mermaid from "mermaid";

mermaid.initialize({
    startOnLoad: true,

});

type Props = {
    chart: any;
    index: number;
}

type State = {
    chart: string;
    index: string;
}
export class Mermaid extends React.Component<Props, State> {

    state = {
        chart: this.props.chart,
        index: this.props.index.toString()
    }
    componentDidMount() {
        this.generateGraph();
    }
    componentDidUpdate(prevProps: Props) {
        if (prevProps.chart !== this.props.chart) {
            this.generateGraph();
        }
    }

    generateGraph(){
        let output = document.getElementById(this.state.index);
        let value = this.props.chart;
        mermaid.parse(value);
        mermaid.render('theGraph'+this.state.index, value, function(svgCode) {
            // @ts-ignore
            output.innerHTML = svgCode;
        });
        mermaid.contentLoaded();
    }


    render() {
        return <div id={this.state.index}/>;
    }
}
