package com.oracle.cloud.baremetal.jenkins.client;

import java.util.List;

import com.oracle.bmc.core.model.*;
import com.oracle.bmc.core.responses.GetSubnetResponse;
import com.oracle.bmc.identity.model.AvailabilityDomain;
import com.oracle.bmc.identity.model.Compartment;
import com.oracle.bmc.identity.model.TagNamespaceSummary;
import com.oracle.bmc.identity.model.Tenancy;
import com.oracle.bmc.model.BmcException;
import com.oracle.cloud.baremetal.jenkins.BaremetalCloudAgentTemplate;

public interface BaremetalCloudClient extends AutoCloseable {

    /**
     * @throws BmcException if an error occurs
     */
    void authenticate() throws BmcException;


    /**
     * Creates an instance with the specified template
     *
     * @param template the instance configuration
     * @param name instance name
     * @return Instance
     * @throws Exception if an error occurs
     */
    Instance createInstance(String name, BaremetalCloudAgentTemplate template) throws Exception;

    /**
     * Creates an instance with the specified instance id
     *
     * @param instanceId the instance id created before
     * @return Instance
     * @throws Exception if an error occurs
     */
    Instance waitForInstanceProvisioningToComplete(String instanceId) throws Exception;

    /**
     * Get an instance public or private ip with the specified instance id
     *
     * @param instanceId the instance id created before
     * @param template baremetal cloud agent template
     * @return the instance ip
     * @throws Exception if an error occurs
     */
    String getInstanceIp(BaremetalCloudAgentTemplate template, String instanceId) throws Exception;

     /**
     * Get the compartment list
     *
     * @return compartment list
     * @throws Exception if an error occurs
     */
    List<Compartment> getCompartmentsList() throws Exception;

    /**Get root compartment
     * @return tenancy
     * @throws Exception if an error occurs
     */
    Tenancy getTenant() throws Exception;

    /**
     * Get the available domain response
     *
     * @param compartmentId the compartment id
     * @return ad list
     * @throws Exception if an error occurs
     */
    List<AvailabilityDomain> getAvailabilityDomainsList(String compartmentId) throws Exception;

    /**
     * Get the available image list
     *
     * @param compartmentId the compartment id
     * @return image list
     * @throws Exception if an error occurs
     */
    List<Image> getImagesList(String compartmentId) throws Exception;

    /**
     * Get the shape list
     *
     * @param compartmentId the compartment id
     * @param availableDomain available domain
     * @param imageId image id
     * @return shape list
     * @throws Exception if an error occurs
     */
    List<Shape> getShapesList(String compartmentId, String availableDomain, String imageId) throws Exception;

    /**
     * Get the OCPUs options
     *
     * @param compartmentId the compartment id
     * @param availableDomain available domain
     * @param imageId image id
     * @param shape shape name
     * @return an array with min (array[0]) and max (array[1])
     * @throws Exception if an error occurs
     */
    Integer[] getMinMaxOcpus(String compartmentId, String availableDomain, String imageId, String shape) throws Exception;

    /**
     * Get the memory options of Flex shapes
     *
     * @param compartmentId the compartment id
     * @param availableDomain available domain
     * @param imageId image id
     * @param shape shape name
     * @return an array with min (array[0]) and max (array[1])
     * @throws Exception if an error occurs
     */
    Integer[] getMinMaxMemory(String compartmentId, String availableDomain, String imageId, String shape) throws Exception;
    /**
     * Get the Virtual Cloud Network list
     *
     * @param tenantId the tenant id
     * @return vcn list
     * @throws Exception if an error occurs
     */
    List<Vcn> getVcnList(String tenantId) throws Exception;

    /**
     * Get the sub net list
     *
     * @param tenantId the tenant id
     * @param vcnId vcn id
     * @return subnet list
     * @throws Exception if an error occurs
     */
    List<Subnet> getSubNetList(String tenantId, String vcnId) throws Exception;

    /**
     * Get the network security group list
     *
     * @param compartmentId the compartment id
     * @return network security group list
     * @throws Exception if an error occurs
     */
    List<NetworkSecurityGroup> getNsgIdsList(String compartmentId) throws Exception;

    /**
     * Get the sub net
     *
     * @param subnetId subnet id
     * @return GetSubnetResponse
     * @throws Exception if an error occurs
     */
    GetSubnetResponse getSubNet(String subnetId) throws Exception;

    /**
     * Terminate instance with the specified instance id
     *
     * @param instanceId the instance id created before
     * @return Opc Request Id
     * @throws Exception if an error occurs
     */
    String terminateInstance(String instanceId) throws Exception;

    /**
     * Wait for the termination with the specified instance id to complete
     *
     * @param instanceId the instance id created before
     * @return Instance
     * @throws Exception if an error occurs
     */
    Instance waitForInstanceTerminationToComplete(String instanceId) throws Exception;

    /**
     * Check instance's state
     *
     * @param instanceId the instance id created before
     * @return Instance.LifecycleState
     * @throws Exception if an error occurs
     */
    Instance.LifecycleState getInstanceState(String instanceId) throws Exception;

    /**
     * Get a list of stopped instances on OCI
     *
     * @param compartmentId the compartment id
     * @param availableDomain available domain
     * @return Instance.LifecycleState
     * @throws Exception if an error occurs
     */
    List<Instance> getStoppedInstances(String compartmentId, String availableDomain) throws Exception;

    /**
     * Stop instance with the specified instance id
     *
     * @param instanceId the instance id created before
     * @return Instance.LifecycleState
     * @throws Exception if an error occurs
     */
    String stopInstance(String instanceId) throws Exception;

    /**
     * Start instance with the specified instance id
     *
     * @param instanceId the instance id created before
     * @return Instance.LifecycleState
     * @throws Exception if an error occurs
     */
    Instance startInstance(String instanceId) throws Exception;

    /**
     * Start instance with the specified instance id
     *
     * @param compartmentId the compartment id
     * @return tag namespace summary list
     * @throws Exception if an error occurs
     */
    List<TagNamespaceSummary> getTagNamespaces(String compartmentId) throws Exception;
}
