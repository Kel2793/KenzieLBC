import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the ListingService.
 */
export default class ListingClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getParameterizedListings','getAllListings', 'createListing', 'deleteListingById', 'updatePrice', 'updateStatus'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    async createListing(address, price, numBedrooms, numBathrooms, squareFootage, listingStatus, lotSize, errorCallback) {
        try {
            const response = await this.client.post(`/listing`, {
                "address": address,
                "squareFootage": squareFootage,
                "price": price,
                "numBedrooms": numBedrooms,
                "numBathrooms": numBathrooms,
                "listingStatus": listingStatus,
                "lotSize": lotSize
            });
            return response.data;
        } catch (error) {
            this.handleError("createListing", error, errorCallback);
        }
    }


    async getParameterizedListings(squareFootage, price, numBedrooms, numBathrooms, lotSize, errorCallback) {

        try {
            const response = await this.client.get(`/listing/query?squareFootage=${squareFootage}&price=${price}&numBedrooms=${numBedrooms}&numBathrooms=${numBathrooms}&lotSize=${lotSize}`);
            return response.data;
        } catch (error) {
            this.handleError("getParameterizedListings", error, errorCallback)
        }
    }

    async getAllListings(errorCallback) {
        try {
            const response = await this.client.get(`/listing`);
            return response.data;
        } catch (error) {
            this.handleError("getAllListings", error, errorCallback)
        }
    }

    async deleteListingById(listingNumber, errorCallback) {
        try {
            const response = await this.client.delete(`/listing/${listingNumber}`);
            return response.data;
        } catch (error) {
            this.handleError("deleteListingById", error, errorCallback)
        }
    }

    async updatePrice(listingNumber, address, squareFootage, price, numBedrooms, numBathrooms, listingStatus, lotSize, errorCallback) {
        try {
            const response = await this.client.put(`/listing/price/${price}`, {
                "listingNumber": listingNumber,
                "address": address,
                "squareFootage": squareFootage,
                "price": price,
                "numBedrooms": numBedrooms,
                "numBathrooms": numBathrooms,
                "listingStatus": listingStatus,
                "lotSize": lotSize
            });
            return response.data;
        } catch (error) {
            this.handleError("updatePrice", error, errorCallback)
        }
    }

    async updateStatus(listingNumber, address, squareFootage, price, numBedrooms, numBathrooms, listingStatus, lotSize, errorCallback) {
        try {
            const response = await this.client.put(`/listing/listingStatus/${listingStatus}`, {
                "listingNumber": listingNumber,
                "address": address,
                "squareFootage": squareFootage,
                "price": price,
                "numBedrooms": numBedrooms,
                "numBathrooms": numBathrooms,
                "listingStatus": listingStatus,
                "lotSize": lotSize
            });
            return response.data;
        } catch (error) {
            this.handleError("updateStatus", error, errorCallback)
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
